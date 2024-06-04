import { Routes } from '@angular/router';
import { StartComponent } from './Components/start/start.component';
import { ListComponent } from './Components/list/list.component';
import { RetiroComponent } from './Components/retiro/retiro.component';
import { ReloadComponent } from './Components/reload/reload.component';

export const routes: Routes = [


  {path: 'start', component: StartComponent},
  {path: '', redirectTo: 'start', pathMatch: 'full'},
  {path: 'start', component: StartComponent},
  {path: 'list', component: ListComponent },
  {path: 'retiro', component: RetiroComponent},
  {path: 'reload', component: ReloadComponent}

];
