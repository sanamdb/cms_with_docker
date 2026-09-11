import { Department } from "./Department";
import { OfferDepartment } from "./OfferDepartment";

export interface CampusDepartment {
    offerDepartment: OfferDepartment;
    department: Department;
}