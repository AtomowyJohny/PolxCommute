import {FormControl} from "@angular/forms";
import {AkumulatorFormName} from "../enum/akumulator-form-name";

export interface AkumulatorForm {
  [AkumulatorFormName.ZNAMIONOWA_ILOSC_CYKLI]: FormControl<number | null>;
  [AkumulatorFormName.POJEMNOSC]: FormControl<number | null>;
}
