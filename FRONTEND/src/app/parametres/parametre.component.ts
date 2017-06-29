import { Component, OnInit } from '@angular/core';

import { GameService } from './../_service/game.service';

@Component({
    moduleId: module.id,
    templateUrl: './parametre.component.html'
})
export class ParametreComponent {

    public stockage: number;
    public nbrTour: number;
    public nbrTourStocker: string = '10';
    private result: any;
    private resultat: any;
    public message: string = '';

    constructor(private _gameService: GameService) { }

    ngOnInit() {
        this.active();
        this._gameService.getParametre()
            .subscribe((data) => {
                this.result = data;
                if (this.result.stockage === 'MySqlDB') {
                    this.stockage = 1;
                }
                else if (this.result.stockage === 'XmlDB') {
                    this.stockage = 2;
                }
                this.nbrTourStocker = this.result.nbrTour;
                this.nbrTour = parseInt(this.nbrTourStocker);
            });
    }

    update() {
        var regex = /^[0-9]+$/i;
        if (this.nbrTour != null) {
            if (regex.test(this.nbrTour + "")) {
                if (this.stockage != null) {
                    this._gameService.parametre(this.nbrTour, this.stockage).subscribe((data) => {
                        this.resultat = data;
                        this.message = 'Success';
                    });
                } else {
                    this.message = 'Le type de stockage ne doit pas étre vide.';
                }
            } else {
                this.message = 'Le nombre de stockage doit étre des numbers Seulement.';
            }
        } else {
            this.message = 'Le nombre tour ne doit pas étre vide.';
        }
        setTimeout(() => {
            this.message = '';
        }, 3000);

    }

    active() {
        document.getElementById('welcome').className = '';
        document.getElementById('jeu').className = '';
        document.getElementById('parametres').className = 'current';
        document.getElementById('regles').className = '';
        document.getElementById('meilleurScore').className = '';
    }
}