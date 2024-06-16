import {inject, Injectable} from '@angular/core';
import {FormControl, FormGroup, NonNullableFormBuilder, Validators} from "@angular/forms";
import {AkumulatorForm} from "../model/akumulator-form";
import {AkumulatorFormName} from "../enum/akumulator-form-name";

@Injectable()
export class AkumulatorFormService {
  private readonly formBuilder: NonNullableFormBuilder = inject(NonNullableFormBuilder);
  private readonly _form: FormGroup<AkumulatorForm> = this.formBuilder.group({
    [AkumulatorFormName.ZNAMIONOWA_ILOSC_CYKLI]: this.formBuilder.control<number | null>(null, {validators: [Validators.required, Validators.max(5000)]}),
    [AkumulatorFormName.POJEMNOSC]: this.formBuilder.control<number | null>(null, {validators: [Validators.required, Validators.min(0)]})
  })

  public get form(): FormGroup<AkumulatorForm> {
    return this._form;
  }

  public get znamionowaIloscCylkiControl(): FormControl<number | null> {
    return this._form.get(AkumulatorFormName.ZNAMIONOWA_ILOSC_CYKLI) as FormControl<number | null>;
  }

  public get pojemnoscControl(): FormControl<number | null> {
    return this._form.get(AkumulatorFormName.POJEMNOSC) as FormControl<number | null>;
  }
}
