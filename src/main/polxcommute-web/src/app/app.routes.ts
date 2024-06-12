import {RouterModule, Routes} from '@angular/router';
import {MechanikListPageComponent} from "./features/mechanik/pages/mechanik-list-page/mechanik-list-page.component";
import {MechanicPanelContainerComponent} from "./features/mechanik/containers/mechanic-panel/mechanic-panel-container.component";
import {NgModel} from "@angular/forms";
import {NgModule} from "@angular/core";

export const routes: Routes = [
  {
    path: 'mechanics', component: MechanikListPageComponent
  },
  {
    path: 'panel/:id', component: MechanicPanelContainerComponent,
  },
  {
    path: '**', redirectTo: 'mechanics'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

