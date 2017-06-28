import { Routes, RouterModule } from '@angular/router';

import { WelcomeComponent } from './welcome/welcome.component';
import { Erreur404Component } from './erreur/erreur404.component';
import { JeuComponent } from './jeu/jeu.component';
import { ParametreComponent } from './parametres/parametre.component';
import { RegleComponent } from './regles/regle.component';
import { MeilleurScoreComponent } from './meilleurScore/meilleurScore.component';

const appRoutes: Routes = [
    { path: '', component: WelcomeComponent },
    { path: 'jeu', component: JeuComponent },
    { path: 'parametres', component: ParametreComponent },
    { path: 'regles', component: RegleComponent },
    { path: 'meilleurScore', component: MeilleurScoreComponent },
    { path: '404', component: Erreur404Component },
    { path: '**', redirectTo: '404' }
];

export const routing = RouterModule.forRoot(appRoutes, { useHash: true });