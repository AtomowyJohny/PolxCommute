import {Component, input, InputSignal} from '@angular/core';
import {Akumulator} from "../../../../api/models/akumulator";

@Component({
  selector: 'app-akumulator-details',
  standalone: true,
  imports: [],
  templateUrl: './akumulator-details.component.html',
  styleUrl: './akumulator-details.component.css'
})
export class AkumulatorDetailsComponent {
  public readonly akumulator :InputSignal<Akumulator> = input.required();
}
