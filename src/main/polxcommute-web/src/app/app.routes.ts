import {RouterModule, Routes} from '@angular/router';
import {MechanikListComponent} from "./features/mechanik/components/mechanik-list/mechanik-list.component";
import {
  MechanicPanelContainerComponent
} from "./features/mechanik/containers/mechanic-panel/mechanic-panel-container.component";
import {NgModule} from "@angular/core";
import {LoginPanelComponent} from "./features/mechanik/containers/login-panel/login-panel.component";

export const routes: Routes = [
  {
    path: 'panel', component: LoginPanelComponent
  },
  {
    path: 'panel/:id', component: MechanicPanelContainerComponent,
  },
  {
    path: '**', redirectTo: 'panel'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

