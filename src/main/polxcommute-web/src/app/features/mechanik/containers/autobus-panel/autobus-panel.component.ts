import {Component, computed, effect, input, InputSignal, Signal} from '@angular/core';
import {Autobus, AutobusElektyczny, AutobusHybrydowy, AutobusSilnikowy} from "../../../../api/models/autobus";
import {AutobusType} from "../../../../enums/autobus-type";
import {ProgressBarComponent} from "../../components/progress-bar/progress-bar.component";
import {AutobusApiService} from "../../../../api/services/autobus-api.service";
import {first} from "rxjs";
import {Akumulator} from "../../../../api/models/akumulator";
import {SelectAccComponent} from "../../components/select-acc/select-acc.component";
import {AkumulatorDetailsComponent} from "../../components/akumulator-details/akumulator-details.component";

@Component({
  selector: 'app-autobus-panel',
  standalone: true,
  imports: [
    ProgressBarComponent,
    SelectAccComponent,
    AkumulatorDetailsComponent
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

  public constructor(private readonly autobusApiService: AutobusApiService) {
    effect(() => {
      this.acumulators = [];
      this.selactedAcc = null;
      this.autobusApiService.getAllAccumulators(this.autobus().id).pipe(first()).subscribe({
        next: aukumulators => this.acumulators = aukumulators
      })
    });
  }

  protected handleAccSelection(acumulatorId: number): void{
    this.selactedAcc = this.acumulators.find((acc) => acc.id === acumulatorId) ?? null;
}
}
