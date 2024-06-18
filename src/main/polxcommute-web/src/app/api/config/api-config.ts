const BACKEND_URL: string = 'http://localhost:8080';
const BUS_PREFIX: string = 'bus';
const CHARGER_PREFIX: string = 'charger';

export const GET_ALL_MECHANICS_URL = `${BACKEND_URL}/mechanic/all`

export function getMechanicDetailsUrl(id: number): string {
  return `${BACKEND_URL}/mechanic/${id}`;
}

export function getMechanikPanelData(id: number): string {
  return `${BACKEND_URL}/data/${id}`;
}

export function getAllMechanicsVehiclesUrl(id: number): string {
  return `${BACKEND_URL}/${BUS_PREFIX}/all/${id}`
}

export function getBusDetailsUrl(id: number): string {
  return `${BACKEND_URL}/${BUS_PREFIX}/details/${id}`
}

export function getAllAcc(id: number): string {
  return `${BACKEND_URL}/${BUS_PREFIX}/acc/${id}`
}

export function getChangeAccUrl(): string {
  return `${BACKEND_URL}/${BUS_PREFIX}/acc`
}

export function getAllChargesUrl(): string {
  return `${BACKEND_URL}/${CHARGER_PREFIX}/modes`
}

export function getChargeActionUrl(idAkumulatora: number, modeId: number): string {
  return `${BACKEND_URL}/${CHARGER_PREFIX}/${idAkumulatora}/action-charger/${modeId}`;
}

