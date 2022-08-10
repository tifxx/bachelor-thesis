import { ILocation } from 'src/app/model/ILocation';
import { Car } from "./Car";
import { User } from "./User";

export interface Reservation 
{
    id: number,
    pickUpLocation: ILocation,
    dropOffLocation: ILocation,
    totalPrice: number,
    car: Car,
    client: User,
    status: string,
    pickUpDate: string,
    dropOffDate: string,
    reviewWritten: Boolean
}