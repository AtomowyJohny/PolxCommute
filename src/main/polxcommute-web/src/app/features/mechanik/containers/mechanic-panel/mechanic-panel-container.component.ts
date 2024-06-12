import {Component, inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {MechanikDetails} from "../../../../api/models/mechanik";
import {MechanikApiService} from "../../../../api/services/mechanik-api.service";
import {combineLatest, first} from "rxjs";
import {MechanikDetailsComponent} from "../../components/mechanik-details/mechanik-details.component";
import {Autobus} from "../../../../api/models/autobus";
import {AutobusApiService} from "../../../../api/services/autobus-api.service";
import {AutobusListComponent} from "../../components/autobus-list/autobus-list.component";
import {MatGridList, MatGridTile} from "@angular/material/grid-list";
import {MatCard} from "@angular/material/card";
import {AutobusDetailsComponent} from "../../components/autobus-details/autobus-details.component";
import {AutobusPanelComponent} from "../autobus-panel/autobus-panel.component";

@Component({
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, MechanikDetailsComponent, AutobusListComponent, MatGridList, MatGridTile, MatCard, AutobusDetailsComponent, AutobusPanelComponent],
  templateUrl: './mechanic-panel-container.component.html',
  styleUrl: './mechanic-panel-container.component.css'
})
export class MechanicPanelContainerComponent implements OnInit {
  protected mechanikDetails?: MechanikDetails;
  protected autobusList: Autobus[] = [];
  protected autobusDetails?: Autobus;

  protected readonly mechanikId: number = inject(MAT_DIALOG_DATA).idMechanik;
  private readonly mechanicApiService: MechanikApiService = inject(MechanikApiService);
  private readonly autobusApiService: AutobusApiService = inject(AutobusApiService);

  ngOnInit() {
    combineLatest([this.mechanicApiService.getMechanicDetails(this.mechanikId).pipe(first()),
      this.autobusApiService.getGllMechanicVehicles(this.mechanikId).pipe(first())])
      .subscribe(([mechanikDetails, autobusList]) => {
        this.mechanikDetails = mechanikDetails;
        this.autobusList = autobusList;
      })
  };

  protected getAutobusDetails(autobusId: number): void {
    this.autobusApiService.getBusDetailsById(autobusId).pipe(first())
      .subscribe(autobus => this.autobusDetails = autobus);
  }
}
