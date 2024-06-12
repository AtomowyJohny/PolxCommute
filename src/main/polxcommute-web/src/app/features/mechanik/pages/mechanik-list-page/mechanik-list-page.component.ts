import {Component, inject, OnInit} from '@angular/core';
import {MechanikApiService} from "../../../../api/services/mechanik-api.service";
import {Mechanik} from "../../../../api/models/mechanik";
import {first} from "rxjs";
import {Router, RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {MatTableModule} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {MechanicPanelContainerComponent} from "../../containers/mechanic-panel/mechanic-panel-container.component";

@Component({
  selector: 'app-mechanik-list-page',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf,
    FormsModule,
    MatTableModule
  ],
  templateUrl: './mechanik-list-page.component.html',
  styleUrl: './mechanik-list-page.component.css'
})
export class MechanikListPageComponent implements OnInit {
  protected mechanicList?: Mechanik[]
  protected readonly displayedColumns: string[] = ['id', 'name'];

  private readonly mechanicApiService: MechanikApiService = inject(MechanikApiService);
  private readonly dialogService: MatDialog = inject(MatDialog);



  public ngOnInit(): void {
    this.mechanicApiService.getAllMechanics().pipe(first()).subscribe({
      next: response => this.mechanicList = response.mechanicList,
      error: err => console.error(err)
    })
  }

  protected onMechanikNameClick(idMechanik: number): void {
    this.dialogService.open(MechanicPanelContainerComponent, {
      data: {
        idMechanik
      }
    })
  }


}
