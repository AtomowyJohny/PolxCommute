import {Component, computed, effect, input, InputSignal, OnInit, Signal} from '@angular/core';
import {Autobus, AutobusElektyczny, AutobusHybrydowy, AutobusSilnikowy} from "../../../../api/models/autobus";
import {AutobusType} from "../../../../enums/autobus-type";
import {ProgressBarComponent} from "../../components/progress-bar/progress-bar.component";
import {AutobusApiService} from "../../../../api/services/autobus-api.service";
import {filter, first, map, Observable, switchMap, tap} from "rxjs";
import {Akumulator} from "../../../../api/models/akumulator";
import {SelectAccComponent} from "../../components/select-acc/select-acc.component";
import {AkumulatorDetailsComponent} from "../../components/akumulator-details/akumulator-details.component";
import {MatButton} from "@angular/material/button";
import {MatDialog} from "@angular/material/dialog";
import {AkumulatorFormComponent} from "../../components/akumulator-form/akumulator-form.component";
import {ChangeAkumulatorReq} from "../../../../api/models/requests/change-akumulator-req";
import {AkumulatorFormName} from "../../components/akumulator-form/enum/akumulator-form-name";
import {ChargerMode} from "../../../../api/models/charger-mode";
import {ChargerApiService} from "../../../../api/services/charger-api.service";
import {ChargerActionComponent} from "../../components/charger-action/charger-action.component";
import {MatDivider} from "@angular/material/divider";

@Component({
  selector: 'app-autobus-panel',
  standalone: true,
  imports: [
    ProgressBarComponent,
    SelectAccComponent,
    AkumulatorDetailsComponent,
    MatButton,
    ChargerActionComponent,
    MatDivider
  ],
  templateUrl: './autobus-panel.component.html',
  styleUrl: './autobus-panel.component.css'
})
export class AutobusPanelComponent implements OnInit {
  public readonly autobus: InputSignal<Autobus> = input.required();

  protected acumulators: Akumulator[] = [];
  protected selactedAcc: Akumulator | null = null;
  protected chargerModes: ChargerMode[] = [];

  protected poziomNaladowania: number = 0;

  protected readonly autobusHybrydowy: Signal<AutobusHybrydowy | null> = computed(() => {
    return this.autobus().details?.autobusType === AutobusType.HYBRYDOWY ? this.autobus().details as AutobusHybrydowy : null;
  })

  protected readonly autobusSilnikowy: Signal<AutobusSilnikowy | null> = computed(() => {
    return this.autobus().details?.autobusType === AutobusType.SPALINOWY ? this.autobus().details as AutobusSilnikowy : null;
  })

  protected readonly autobusElektryczny: Signal<AutobusElektyczny | null> = computed(() => {
    return this.autobus().details?.autobusType === AutobusType.ELEKTRYCZNY ? this.autobus().details as AutobusElektyczny : null;
  })

  public constructor(private readonly autobusApiService: AutobusApiService,
                     private readonly chargerApiService: ChargerApiService,
                     private readonly matDialog: MatDialog) {
    effect(() => {
      this.acumulators = [];
      this.selactedAcc = null;
      this.getAllAcumulators().subscribe()
    });
  }

  public ngOnInit(): void {
    this.chargerApiService.getAllChargerModesDictionary().pipe(first()).subscribe({
      next: modes => this.chargerModes = modes
    })
  }

  protected handleAccSelection(acumulatorId: number): void {
    if (this.autobusElektryczny()) {
      this.poziomNaladowania = this.autobusElektryczny()?.poziomNaladowania ?? 0;
    }

    if (this.autobusHybrydowy()) {
      this.poziomNaladowania = this.autobusHybrydowy()?.poziomNaladowania ?? 0;
    }

    this.selactedAcc = this.acumulators.find((acc) => acc.id === acumulatorId) ?? null;
  }

  protected handleChargeAction(modelId: number): void {
    this.chargerApiService.chargeAction(this.selactedAcc!.id, modelId).pipe(first()).subscribe(value => {
      if (this.poziomNaladowania > value) {
        const disChargeInterval = setInterval(() => {
          if (this.poziomNaladowania > value) {
            this.poziomNaladowania = this.poziomNaladowania - 1 < 0 ? 0 : this.poziomNaladowania - 1;
          } else {
            clearInterval(disChargeInterval);
          }

        }, 100)
      }

      if ( this.poziomNaladowania < value) {
        const chargeInterval = setInterval(() => {
          if (this.poziomNaladowania < value) {
            this.poziomNaladowania = this.poziomNaladowania + 1 > 100 ? 100 : this.poziomNaladowania + 1;
          } else {
            clearInterval(chargeInterval);
          }

        }, 100)
      }
    })
  }

  protected handleAkumulatorChange(): void {
    this.matDialog.open(AkumulatorFormComponent, {
      disableClose: true
    }).afterClosed().pipe(
      first(),
      filter(formData => !!formData),
      map(formData => this._mapFormResultToChangeRequest(formData)),
      switchMap(request => this.autobusApiService.changeAutobusAkumulator(request).pipe(first())),
      switchMap(() => this.getAllAcumulators())).subscribe();
  }

  private getAllAcumulators(): Observable<Akumulator[]> {
    return this.autobusApiService.getAllAccumulators(this.autobus().id).pipe(first(), tap({
      next: aukumulators => this.acumulators = aukumulators
    }));
  }

  private _mapFormResultToChangeRequest(formResult: {
    [AkumulatorFormName.ZNAMIONOWA_ILOSC_CYKLI]: number,
    [AkumulatorFormName.POJEMNOSC]: number
  }): ChangeAkumulatorReq {
    return {
      pojemnosc: formResult[AkumulatorFormName.POJEMNOSC],
      znamionowaIloscCykli: formResult[AkumulatorFormName.ZNAMIONOWA_ILOSC_CYKLI],
      idAutobusu: this.autobus().id,
      idAkumulatoraDoWymiany: this.selactedAcc!.id
    }
  }
}
