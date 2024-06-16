import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {AkumulatorFormName} from "./enum/akumulator-form-name";
import {AkumulatorForm} from "./model/akumulator-form";
import {AkumulatorFormService} from "./form-service/akumulator-form.service";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-akumulator-form',
  standalone: true,
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatDialogContent, MatDialogActions, MatButton, MatDialogClose, MatDialogTitle],
  templateUrl: './akumulator-form.component.html',
  styleUrl: './akumulator-form.component.css',
  providers: [AkumulatorFormService]
})
export class AkumulatorFormComponent {
  private readonly formService: AkumulatorFormService = inject(AkumulatorFormService);

  protected readonly formGroup: FormGroup<AkumulatorForm> = this.formService.form;
  protected readonly pojemnoscControl: FormControl<number | null> = this.formService.pojemnoscControl
  protected readonly znamionowaIloscCykliControl: FormControl<number | null> = this.formService.znamionowaIloscCylkiControl
  protected readonly formName: typeof AkumulatorFormName = AkumulatorFormName;

  private readonly dialogRef: MatDialogRef<AkumulatorFormComponent> = inject(MatDialogRef);

  protected saveForm(): void {
    this.formGroup.markAllAsTouched();
    if(this.formGroup.valid) {
      this.dialogRef.close(this.formGroup.value);
    }
  }
}
