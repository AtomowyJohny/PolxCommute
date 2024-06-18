import {inject, Injectable, Signal, signal, WritableSignal} from '@angular/core';
import {ChargerApiService} from "../../../api/services/charger-api.service";
import {first} from "rxjs";
import {Akumulator} from "../../../api/models/akumulator";

interface AkumulatorCharge {
  akumulatorId: number;
  isStatusChangeInProgress: boolean;
  currentPoziomNaladowania: number;
}

@Injectable({
  providedIn: 'root'
})
export class AkumulatorChargeService {
  private readonly _akumulatorCharge: WritableSignal<AkumulatorCharge | null> = signal(null);

  private readonly chargerApiService: ChargerApiService = inject(ChargerApiService);

  // public get akumulatorCharges(): Signal<AkumulatorCharge> {
  //   return this._akumulatorCharge;
  // }

  // public prepareChargerList(akumulators: Akumulator[]): void {
  //   akumulators.reduce((accumulator: AkumulatorCharge[], currentValue) => {
  //
  //     if (!accumulator.find((item) => item.akumulatorId === currentValue.id)) {
  //       accumulator.push({
  //         akumulatorId: currentValue.id,
  //         currentPoziomNaladowania: 0,
  //         isStatusChangeInProgress: false
  //       })
  //     }
  //
  //   }, [])
  // }

  public handleChargeAction(akumulatorId: number, modeId: number): void {
    this.chargerApiService.chargeAction(akumulatorId, modeId).pipe(first()).subscribe();
  }
}
