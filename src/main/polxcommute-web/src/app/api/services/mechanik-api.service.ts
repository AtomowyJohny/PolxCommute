import {inject, Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {GetAllMechanicsResponse} from "../models/responses/get-all-mechanics-response";
import {HttpClient} from "@angular/common/http";
import {GET_ALL_MECHANICS_URL, getMechanicDetailsUrl} from "../config/api-config";
import {MechanikDetails} from "../models/mechanik";

@Injectable({
  providedIn: 'root'
})
export class MechanikApiService {

  private readonly http: HttpClient = inject(HttpClient);

  public getAllMechanics(): Observable<GetAllMechanicsResponse> {
    return this.http.get<GetAllMechanicsResponse>(GET_ALL_MECHANICS_URL);
  }

  public getMechanicDetails(id: number): Observable<MechanikDetails> {
    return this.http.get<MechanikDetails>(getMechanicDetailsUrl(id))
  }
}
