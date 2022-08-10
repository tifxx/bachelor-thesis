import { NumberInput } from "@angular/cdk/coercion";
import { Seats } from "./enum/Seats";
import { Transmission } from "./enum/Transmission";

export interface Car {
    id: number,
    model: string,
    priceForADay: number,
    image: string,
    withInsurance: Boolean,
    withAirConditioner: Boolean,
    seats: Seats,
    transmission: Transmission,
    totalPrice: number
    sumRating: number
    numReviews: number
    totalRating: string
}