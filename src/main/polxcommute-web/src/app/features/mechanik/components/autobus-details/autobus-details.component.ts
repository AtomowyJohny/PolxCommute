import {Component, Input} from '@angular/core';
import {Autobus, AutobusElektyczny, AutobusHybrydowy, AutobusSilnikowy} from "../../../../api/models/autobus";
import {AutobusType} from "../../../../enums/autobus-type";

@Component({
  selector: 'app-autobus-details',
  standalone: true,
  imports: [],
  templateUrl: './autobus-details.component.html',
  styleUrl: './autobus-details.component.css'
})
export class AutobusDetailsComponent {
  protected autobus?: Autobus
  protected autobusElektryczny: AutobusElektyczny | null = null;
  protected autobusSilnikowy: AutobusSilnikowy | null = null;
  protected autobusHybrydowy: AutobusHybrydowy | null = null;

  @Input({required: true, alias: 'autobus'})
  public set setAutobus(autobus: Autobus) {
    this.autobus = autobus;
    this.autobusElektryczny = null;
    this.autobusHybrydowy = null;
    this.autobusSilnikowy = null;


    switch (this.autobus.details?.autobusType) {
      case AutobusType.ELEKTRYCZNY:
        this.autobusElektryczny = this.autobus.details as AutobusElektyczny
        break;
      case AutobusType.HYBRYDOWY:
        this.autobusHybrydowy = this.autobus.details as AutobusHybrydowy
        break;
      case AutobusType.SPALINOWY:
        this.autobusSilnikowy = this.autobus.details as AutobusSilnikowy
        break;
      default:
        console.error('Unsupported bus type!');
    }
  };

}
