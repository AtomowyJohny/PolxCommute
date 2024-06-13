import {Component, OnInit} from '@angular/core';
import {first} from "rxjs";
import {MechanikApiService} from "../../../../api/services/mechanik-api.service";
import {inject} from "@angular/core";
import {Mechanik} from "../../../../api/models/mechanik";
import {MechanikListComponent} from "../../components/mechanik-list/mechanik-list.component";

@Component({
  selector: 'app-login-panel',
  standalone: true,
  imports: [
    MechanikListComponent
  ],
  templateUrl: './login-panel.component.html',
  styleUrl: './login-panel.component.css'
})
export class LoginPanelComponent implements OnInit {
  protected mechanicList?: Mechanik[]
  private readonly mechanicApiService: MechanikApiService = inject(MechanikApiService);

  public ngOnInit(): void {
    this.mechanicApiService.getAllMechanics().pipe(first()).subscribe({
      next: response => this.mechanicList = response.mechanicList,
      error: err => console.error(err)
    })
  }
}
