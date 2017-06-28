import { Component, OnInit } from '@angular/core';

import { GameService } from './../_service/game.service';

@Component({
    moduleId: module.id,
    templateUrl: './meilleurScore.component.html'
})
export class MeilleurScoreComponent {

    public pseudo: string = '';
    public score: string = '';
    public nbrTour: string = '';
    public empty: boolean = false;
    private result: any;

    constructor(private _gameService: GameService) { }

    ngOnInit() {
        this.active();
        this._gameService.getMeilleurScore()
            .subscribe((data) => {
                this.result = data;
                if (this.result.message === 'Success') {
                    this.pseudo = this.result.pseudo;
                    this.score = this.result.score;
                    this.nbrTour = this.result.nbrTour;
                    this.empty = false;
                } else {
                    this.empty = true;
                }
            });
    }

    active() {
        document.getElementById('welcome').className = '';
        document.getElementById('jeu').className = '';
        document.getElementById('parametres').className = '';
        document.getElementById('regles').className = '';
        document.getElementById('meilleurScore').className = 'current';
    }

}