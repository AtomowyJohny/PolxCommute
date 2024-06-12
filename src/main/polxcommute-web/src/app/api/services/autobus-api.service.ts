import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GetGllMechanicVehiclesResponse} from "../models/responses/get-gll-mechanic-vehicles-response";
import {map, Observable} from "rxjs";
import {getAllMechanicsVehiclesUrl, getBusDetailsUrl} from "../config/api-config";
import {Autobus} from "../models/autobus";

@Injectable({
  providedIn: 'root'
})
export class AutobusApiService {
  private readonly http: HttpClient = inject(HttpClient);


  public getGllMechanicVehicles(idMechanik: number): Observable<Autobus[]> {
    return this.http.get<GetGllMechanicVehiclesResponse>(getAllMechanicsVehiclesUrl(idMechanik)).pipe(map(response => response.autobusList));
  }

  public getBusDetailsById(busId: number): Observable<Autobus> {
    return this.http.get<Autobus>(getBusDetailsUrl(busId));
  }
}
