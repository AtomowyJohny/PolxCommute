import {Component, input, InputSignal, output, OutputEmitterRef} from '@angular/core';
import {ChargerMode} from "../../../../api/models/charger-mode";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-charger-action',
  standalone: true,
  imports: [
    MatFormField,
    MatLabel,
    MatOption,
    MatSelect,
    MatButton
  ],
  templateUrl: './charger-action.component.html',
  styleUrl: './charger-action.component.css'
})
export class ChargerActionComponent {
  public readonly chargerModes: InputSignal<ChargerMode[]> = input.required();
  public readonly onSelection: OutputEmitterRef<number> = output();
}
