import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { routing } from './app.routing';
import { HttpModule, BaseRequestOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';

import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { Erreur404Component } from './erreur/erreur404.component';
import { JeuComponent } from './jeu/jeu.component';
import { ParametreComponent } from './parametres/parametre.component';
import { RegleComponent } from './regles/regle.component';
import { MeilleurScoreComponent } from './meilleurScore/meilleurScore.component';


import { Url }from './_service/url';
import { GameService }from './_service/game.service';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    Erreur404Component,
    JeuComponent,
    ParametreComponent,
    RegleComponent,
    MeilleurScoreComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    MockBackend,
    BaseRequestOptions,
    GameService,
    Url
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
