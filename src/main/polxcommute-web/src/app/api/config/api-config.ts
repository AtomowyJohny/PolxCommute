const BACKEND_URL: string = 'http://localhost:8080';

export const GET_ALL_MECHANICS_URL = `${BACKEND_URL}/mechanic/all`

export function  getMechanicDetailsUrl(id: number): string {
  return `${BACKEND_URL}/${id}`;
}
