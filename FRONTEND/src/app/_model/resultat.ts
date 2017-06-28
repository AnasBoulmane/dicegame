import { De } from './de'

export interface Resultat {
    de1: De;
    de2: De;
    score: number;
    meilleurScore: number;
    pseudoMeilleurScore: string;
}