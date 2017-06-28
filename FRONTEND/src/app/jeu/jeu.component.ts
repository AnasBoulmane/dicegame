import { Component, OnInit } from '@angular/core';

import { Resultat } from './../_model/resultat'
import { GameService } from './../_service/game.service';

@Component({
    moduleId: module.id,
    templateUrl: './jeu.component.html'
})
export class JeuComponent {

    public score: number = 0;
    public de1: string = '';
    public de2: string = '';
    public meilleurScore: string = '';
    public nbrTourDefault: string = '';
    public jeu: boolean = true;
    public play: boolean = false;
    public result: string = '';
    public msgErreur: string = '';
    public pseudo: string = '';
    public nbrTour: number = 0;
    public nbrTourTotal: number = 10;
    private resultat: any;
    public empty: boolean = false;
    public good: string = '';
    public resultOneTour: Resultat;
    public imageDe: boolean = false;
    private login: any;
    public buttonJeu: string = 'Lancer';
    public scoreLancer: number = 0;
    public afficherButton: boolean = true;

    constructor(private _gameService: GameService) { }

    ngOnInit() {
        this.active();
        this._gameService.getMeilleurScore()
            .subscribe((data) => {
                this.resultat = data;
                if (this.resultat.message === 'Success') {
                    this.meilleurScore = this.resultat.score;
                    this.nbrTourDefault = this.resultat.nbrTour;
                    this.nbrTourTotal = parseInt(this.nbrTourDefault);
                    this.empty = false;
                } else {
                    this.empty = true;
                }
            });
    }

    rejouer() {
        this.scoreLancer = 0;
        this.buttonJeu = 'Lancer';
        this.pseudo = '';
        this.msgErreur = '';
        this.nbrTour = 0;
        this.jeu = true;
        this.result = '';
        this.play = false;
        this.score = 0;
        this.ngOnInit();
    }

    jouer() {
        var regex = /^[a-zA-Z]+$/i;
        if (this.pseudo !== '') {
            if (regex.test(this.pseudo)) {
                if (this.pseudo.length >= 4) {
                    this._gameService.login()
                        .subscribe((data) => {
                            this.login = data;
                            if (this.login.message === 'Success') {
                                this.jeu = false;
                                this.result = '';
                                this.play = true;
                            }
                        });
                } else {
                    this.msgErreur = "Le pseudo doit contenir au minimum 4 caractère.";
                }
            } else {
                this.msgErreur = "La format du pseudo incorrect ( Il ne doit pas contenir les caractères speciaux ou les espaces ).";
            }
        } else {
            this.msgErreur = "Le champs ne doit pas étre vide.";
        }
        setTimeout(() => { this.msgErreur = ''; }, 3000);

    }

    lancer() {
        this.afficherButton = false;
        this.good = '';
        this.scoreLancer = 0;
        if ((this.nbrTour + 1) === this.nbrTourTotal) {
            this.buttonJeu = 'Résultat';
        }
        if (this.nbrTour === this.nbrTourTotal) {
            if (this.score >= parseInt(this.meilleurScore)) {
                this.result = 'v';
            } else {
                this.result = 'p';
            }
            this.jeu = false;
            this.play = false;
        }
        this._gameService.lancer(this.pseudo)
            .subscribe((data) => {
                this.resultOneTour = data;
                if (this.resultOneTour !== null) {
                    this.imageDe = true;
                    this.de1 = "./assets/images/animation.gif";
                    this.de2 = "./assets/images/animation.gif";
                    setTimeout(() => {
                        this.de1 = "./assets/images/" + this.resultOneTour.de1.etat + ".png";
                        this.de2 = "./assets/images/" + this.resultOneTour.de2.etat + ".png";
                        if (this.resultOneTour.de1.etat + this.resultOneTour.de2.etat == 7) {
                            this.good = 'up';
                        } else {
                            this.good = 'down';
                        }
                        this.afficherButton = true;
                        this.score = this.resultOneTour.score;
                        this.scoreLancer = this.resultOneTour.de1.etat + this.resultOneTour.de2.etat;
                        this.nbrTour++;
                    }, 3000);
                    this.empty = false;
                } else {
                    this.empty = true;
                }
            });
    }



    active() {
        document.getElementById('welcome').className = '';
        document.getElementById('jeu').className = 'current';
        document.getElementById('parametres').className = '';
        document.getElementById('regles').className = '';
        document.getElementById('meilleurScore').className = '';
    }
}