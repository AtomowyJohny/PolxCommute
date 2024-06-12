import {Component, computed, effect, input, InputSignal, Signal} from '@angular/core';
import {Autobus, AutobusElektyczny, AutobusHybrydowy, AutobusSilnikowy} from "../../../../api/models/autobus";
import {AutobusType} from "../../../../enums/autobus-type";
import {ProgressBarComponent} from "../../components/progress-bar/progress-bar.component";

@Component({
  selector: 'app-autobus-panel',
  standalone: true,
  imports: [
    ProgressBarComponent
  ],
  templateUrl: './autobus-panel.component.html',
  styleUrl: './autobus-panel.component.css'
})
export class AutobusPanelComponent {
  public readonly autobus: InputSignal<Autobus> = input.required();

  protected readonly autobusHybrydowy: Signal<AutobusHybrydowy | null> = computed(() => {
    return this.autobus().details?.autobusType === AutobusType.HYBRYDOWY ? this.autobus().details as AutobusHybrydowy : null;
  })

  protected readonly autobusSilnikowy: Signal<AutobusSilnikowy | null> = computed(() => {
    return this.autobus().details?.autobusType === AutobusType.SPALINOWY ? this.autobus().details as AutobusSilnikowy : null;
  })

  protected readonly autobusElektryczny: Signal<AutobusElektyczny | null> = computed(() => {
    return this.autobus().details?.autobusType === AutobusType.ELEKTRYCZNY ? this.autobus().details as AutobusElektyczny : null;
  })
}
