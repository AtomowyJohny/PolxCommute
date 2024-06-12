export interface Mechanik {
  id: number;
  name: string;
  surname: string;
}


export interface MechanikDetails extends Mechanik {
  dataZatrudnienia: Date;
  stopienDoswiadczenia: number;
  premia: number;
  dodatekDoPremiiZaDoswiadczenie: number
}
