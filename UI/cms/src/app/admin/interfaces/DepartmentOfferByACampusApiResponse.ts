import { Campus } from "./Campus";
import { CampusDepartment } from "./CampusDepartment";

export interface DepartmentOfferByACampusApiResponse {
    campus: Campus;
    campusDepartment: CampusDepartment[];
}