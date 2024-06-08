import {Component, inject, OnInit} from '@angular/core';
import {MechanikApiService} from "../../../../api/services/mechanik-api.service";
import {Mechanik} from "../../../../api/models/mechanik";
import {first} from "rxjs";

@Component({
  selector: 'app-mechanik-list-page',
  standalone: true,
  imports: [],
  templateUrl: './mechanik-list-page.component.html',
  styleUrl: './mechanik-list-page.component.css'
})
export class MechanikListPageComponent implements OnInit {
  protected mechanicList?: Mechanik[]

  private readonly mechanicApiService: MechanikApiService = inject(MechanikApiService);

  public ngOnInit(): void {
    this.mechanicApiService.getAllMechanics().pipe(first()).subscribe({
      next: response => this.mechanicList = response.mechanicList,
      error: err => console.error(err)
    })
  }
}
