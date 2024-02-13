import { useQuery } from "@tanstack/react-query";
import fetchBreedList from "./fetchBreedList";

const localCache = {};

export default function useBreedList(animal) {
  const results = useQuery(["breeds", animal], fetchBreedList);
  return [results?.data?.breeds ?? [], fetchBreedList()];
}
