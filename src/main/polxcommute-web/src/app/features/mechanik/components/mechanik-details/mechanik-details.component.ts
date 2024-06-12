import {Component, Input} from '@angular/core';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {faUser} from '@fortawesome/free-solid-svg-icons';
import {MechanikDetails} from "../../../../api/models/mechanik";

@Component({
  selector: 'app-mechanik-details',
  standalone: true,
  imports: [FontAwesomeModule,],
  templateUrl: './mechanik-details.component.html',
  styleUrl: './mechanik-details.component.css'
})
export class MechanikDetailsComponent {
  @Input({required: true}) public mechanikDetails!: MechanikDetails
  protected readonly faUser = faUser;
}
