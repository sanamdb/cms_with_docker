export interface Campus {
  id: string;
  name: string;
  address:string;
  city: string;
  establishedDate: string;
  campusPicture: string; 
}

interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

interface Pageable {
  offset: number;
  pageNumber: number;
  pageSize: number;
  paged: boolean;
  sort:Sort;
  unpaged: boolean
}

export interface CampusApiResponse {
  content: Campus[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageable: Pageable;
  size: number;
  sort: Sort;
  totalElements: number;
  totalPages: number;
}