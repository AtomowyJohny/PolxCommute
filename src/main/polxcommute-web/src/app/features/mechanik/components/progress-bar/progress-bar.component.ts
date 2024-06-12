import {Component, Input} from '@angular/core';
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatLabel} from "@angular/material/form-field";

@Component({
  selector: 'app-progress-bar',
  standalone: true,
  imports: [MatProgressBarModule, MatLabel],
  templateUrl: './progress-bar.component.html',
  styleUrl: './progress-bar.component.css'
})
export class ProgressBarComponent {
  @Input({required: true}) public value!: number;
  @Input() public label: string = '';
}
