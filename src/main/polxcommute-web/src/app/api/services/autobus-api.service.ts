import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GetGllMechanicVehiclesResponse} from "../models/responses/get-gll-mechanic-vehicles-response";
import {map, Observable} from "rxjs";
import {getAllAcc, getAllMechanicsVehiclesUrl, getBusDetailsUrl, getChangeAccUrl} from "../config/api-config";
import {Autobus} from "../models/autobus";
import {Akumulator} from "../models/akumulator";
import {GetAllBusAccumulatorsResponse} from "../models/responses/get-all-bus-accumulators";
import {ChangeAkumulatorReq} from "../models/requests/change-akumulator-req";

@Injectable({
  providedIn: 'root'
})
export class AutobusApiService {
  private readonly http: HttpClient = inject(HttpClient);

  public getGllMechanicVehicles(idMechanik: number): Observable<Autobus[]> {
    return this.http.get<GetGllMechanicVehiclesResponse>(getAllMechanicsVehiclesUrl(idMechanik)).pipe(map(response => response.autobusList));
  }

  public getAllAccumulators(busId: number): Observable<Akumulator[]> {
    return this.http.get<GetAllBusAccumulatorsResponse>(getAllAcc(busId)).pipe(map(res => res.acumulators));
  }

  public getBusDetailsById(busId: number): Observable<Autobus> {
    return this.http.get<Autobus>(getBusDetailsUrl(busId));
  }

  public changeAutobusAkumulator(request: ChangeAkumulatorReq): Observable<void> {
    return this.http.patch<void>(getChangeAccUrl(), request);
  }
}
