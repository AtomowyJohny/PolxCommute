import {Component, computed, effect, input, InputSignal, Signal} from '@angular/core';
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

@Component({
  selector: 'app-autobus-panel',
  standalone: true,
  imports: [
    ProgressBarComponent,
    SelectAccComponent,
    AkumulatorDetailsComponent,
    MatButton
  ],
  templateUrl: './autobus-panel.component.html',
  styleUrl: './autobus-panel.component.css'
})
export class AutobusPanelComponent {
  public readonly autobus: InputSignal<Autobus> = input.required();

  protected acumulators: Akumulator[] = [];
  protected selactedAcc: Akumulator | null = null;


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
                     private readonly matDialog: MatDialog) {
    effect(() => {
      this.acumulators = [];
      this.selactedAcc = null;
      this.getAllAcumulators().subscribe()
    });
  }

  protected handleAccSelection(acumulatorId: number): void {
    this.selactedAcc = this.acumulators.find((acc) => acc.id === acumulatorId) ?? null;
  }

  protected handleAkumulatorChange(): void {
    console.log(this.autobus().id);
    this.matDialog.open(AkumulatorFormComponent, {
      disableClose: true
    }).afterClosed().pipe(
      first(),
      filter(result => !!result),
      map(this._mapFormResultToChangeRequest),
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
