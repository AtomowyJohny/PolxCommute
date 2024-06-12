import {AutobusType} from "../../enums/autobus-type";

export interface Autobus {
  id: number,
  model: string
  rokProdukcji: number;
  mocNetto: number;
  zasieg: number;
  przebieg: number;
  iloscMiejsc: number;
  details?: AutobusDetails;
}

export interface AutobusDetails {
  id: number;
  autobusType: AutobusType
}

export interface  AutobusElektyczny extends AutobusDetails {
  iloscPakietowZasilajacych: number;
  poziomNaladowania: number;
}

export interface AutobusSilnikowy extends AutobusDetails {
  iloscKoni: number;
  spalanie: number;
  pojemnoscZbiornika: number;
  typPaliwa: string;
  iloscPaliwa: number;
}

export interface AutobusHybrydowy extends AutobusDetails {
  iloscPakietowZasilajacych: number;
  poziomNaladowania: number;

  iloscKoni: number;
  spalanie: number;
  pojemnoscZbiornika: number;
  typPaliwa: string;
  iloscPaliwa: number;

  typNapeduHybrydowego: string;
  ladowaniePlugIn: boolean
}
