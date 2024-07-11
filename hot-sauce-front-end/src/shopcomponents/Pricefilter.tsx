import { PriceRangeFilter } from "./PriceRangeFilter";

export const Pricefilter = ({ addPriceFilter }) => {
  return (
    <div>
      <p className="font-bold border-b-2 border-jalapeno">Filter by price:</p>
      <PriceRangeFilter label={"min"} addPriceFilter={addPriceFilter} />
      <PriceRangeFilter label={"max"} addPriceFilter={addPriceFilter} />
    </div>
  );
};
