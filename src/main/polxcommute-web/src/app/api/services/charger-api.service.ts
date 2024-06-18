import {inject, Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {ChargerMode} from "../models/charger-mode";
import {HttpClient} from "@angular/common/http";
import {GetAllChargerModeResponse} from "../models/responses/get-all-charger-mode-response";
import {getAllChargesUrl, getChargeActionUrl} from "../config/api-config";
import {ChargeActionResponse} from "../models/responses/charge-action-response";

@Injectable({
  providedIn: 'root'
})
export class ChargerApiService {
  private readonly http: HttpClient = inject(HttpClient);

  public getAllChargerModesDictionary(): Observable<ChargerMode[]> {
    return this.http.get<GetAllChargerModeResponse>(getAllChargesUrl()).pipe(map(response => response.chargerModes));
  }

  public chargeAction(idAkumulatora: number, modeId: number): Observable<number> {
    return this.http.patch<ChargeActionResponse>(getChargeActionUrl(idAkumulatora, modeId), {}).pipe(map(response => response.chargeLevel))
  }
}

