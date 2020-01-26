export class Car {
    id:number;
    year:number;
    licensePlate:string;
    model:string;
    color:string;

    constructor(id:number,
        year:number,
        licensePlate:string,
        model:string,
        color:string){
            this.id = id;
            this.year = year;
            this.licensePlate = licensePlate;
            this.model = model;
            this.color = color;
        }
}