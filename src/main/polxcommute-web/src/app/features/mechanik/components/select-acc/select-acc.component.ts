import {Component, effect, input, InputSignal, output, OutputEmitterRef} from '@angular/core';
import {Akumulator} from "../../../../api/models/akumulator";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";


@Component({
  selector: 'app-select-acc',
  standalone: true,
  imports: [
    MatFormFieldModule, MatSelectModule
  ],
  templateUrl: './select-acc.component.html',
  styleUrl: './select-acc.component.css'
})
export class SelectAccComponent {
  public readonly acumulators: InputSignal<Akumulator[]> = input.required();
  public readonly onSelection: OutputEmitterRef<number> = output();

  constructor() {
    effect(() => {
      const acumulators: Akumulator[] = this.acumulators();
      if(acumulators && acumulators.length) {
        this.onSelection.emit(acumulators[0].id);
      }
    });
  }

}



