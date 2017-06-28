import { Component } from '@angular/core';

@Component({
    moduleId: module.id,
    templateUrl: './jeu.component.html'
})
export class JeuComponent {
    jeu: boolean = true;
    play: boolean = false;
    result: boolean = false;

    msgErreur: string = '';
    pseudo: string = '';
    nbrTour: number = 1;
    nbrTourTotal: number = 10;

    rejouer() {
        this.pseudo = '';
        this.msgErreur = '';
        this.nbrTour = 1;
        this.jeu = true;
        this.result = false;
        this.play = false;
    }

    jouer() {
        var regex = /^[a-z0-9]+$/i;
        if (this.pseudo !== '') {
            if (regex.test(this.pseudo)) {
                if (this.pseudo.length >= 4) {
                    this.jeu = false;
                    this.result = false;
                    this.play = true;
                } else {
                    this.msgErreur = "Le pseudo doit contenir au minimum 4 caractère.";
                }
            } else {
                this.msgErreur = "La format du pseudo incorrect ( Il ne doit pas contenir les caractères speciaux ou les espaces ).";
            }
        } else {
            this.msgErreur = "Le champs ne doit pas étre vide.";
        }
        setInterval(() => { this.msgErreur = ''; }, 5000);

    }

    lancer() {
        this.nbrTour++;
        if (this.nbrTour > this.nbrTourTotal) {
            this.jeu = false;
            this.play = false;
            this.result = true;
        }
    }
}