import {Routes} from '@angular/router';
import {MechanikListPageComponent} from "./features/mechanik/pages/mechanik-list-page/mechanik-list-page.component";

export const routes: Routes = [
  {
    path: 'mechanics',
    component: MechanikListPageComponent
  },
  {
    path: '**',
    redirectTo: 'mechanics'
  }
];
