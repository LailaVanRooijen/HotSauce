import { useState, useEffect } from "react";

export const PriceRangeFilter = ({
  label,
  addPriceFilter,
}: {
  label: string;
  addPriceFilter: (label: string, minmax: number) => void;
}) => {
  const [amount, setAmount] = useState<number>(0);

  useEffect(() => {
    addPriceFilter(label, amount);
  }, [amount]);

  return (
    <div className="flex items-center justify-between">
      <label className="capitalize" htmlFor={label}>
        {label}
      </label>
      <input
        className="border-2 px-2 rounded-md m-2 w-1/3"
        type="number"
        value={amount}
        onChange={(e) => {
          if (Number(e.target.value) < 0 || Number(e.target.value) > 200) {
            console.error("amount must be between 0 and 200");
          } else {
            setAmount(Number(e.target.value));
          }
        }}
      />
    </div>
  );
};
