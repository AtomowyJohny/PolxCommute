import {Component, EventEmitter, inject, Input, OnInit, Output} from '@angular/core';
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
  templateUrl: './mechanik-list.component.html',
  styleUrl: './mechanik-list.component.css'
})
export class MechanikListComponent implements OnInit {
  @Input({required: true}) public mechanicList: Mechanik[] = [];
  @Output() public readonly mechanikSelected: EventEmitter<number> = new EventEmitter<number>();


  // protected mechanicList?: Mechanik[]
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
    this.mechanikSelected.emit(idMechanik)

    this.dialogService.open(MechanicPanelContainerComponent, {
      data: {
        idMechanik
      }
    })
  }

}
