const BACKEND_URL: string = 'http://localhost:8080';

export const GET_ALL_MECHANICS_URL = `${BACKEND_URL}/mechanic/all`

export function  getMechanicDetailsUrl(id: number): string {
  return `${BACKEND_URL}/mechanic/${id}`;
}

export function getMechanikPanelData(id: number): string{
  return `${BACKEND_URL}/data/${id}`;
}

export function getAllMechanicsVehiclesUrl(id: number): string{
  return `${BACKEND_URL}/bus/all/${id}`
}

export function getBusDetailsUrl(id: number): string {
  return `${BACKEND_URL}/bus/details/${id}`
}

export function getAllAcc(id: number): string{
  return `${BACKEND_URL}/bus/acc/${id}`
}
