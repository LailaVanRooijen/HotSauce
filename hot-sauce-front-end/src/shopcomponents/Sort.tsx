export const Sort = ({ style }) => {
  return (
    <div className={style}>
      <select
        aria-label="sort by"
        name="sortBy"
        id="sortBy"
        className="rounded bg-white px-2 py-1 text-jalapeno border-2 border-jalapeno"
      >
        <option value="recommended">recommended</option>
        <option value="priceHighLow">price high - low</option>
        <option value="priceLowHigh">price low - high</option>
        <option value="alphabetAZ">alphabet a - z</option>
        <option value="alphabetZA">alphabet z - a</option>
      </select>
    </div>
  );
};
