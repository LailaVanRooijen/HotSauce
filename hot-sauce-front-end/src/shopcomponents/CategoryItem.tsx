export const CategoryItem: React.FC<CategoryItem> = ({
  category,
  name,
  filterCategory,
}) => {
  return (
    <dd className="mt-2">
      <input
        className="before:content[''] before:bg-blue-gray-500 checked:primary peer relative mr-2 h-5 w-5 cursor-pointer appearance-none rounded-md border border-primary transition-all before:absolute before:left-2/4 before:top-2/4 before:block before:h-12 before:w-12 before:-translate-x-2/4 before:-translate-y-2/4 before:rounded-full before:opacity-0 before:transition-opacity after:absolute after:left-0 after:top-0 after:flex after:h-full after:w-full after:items-center after:justify-center checked:after:text-white checked:after:content-['🌶'] hover:before:opacity-10"
        type="checkbox"
        id={name}
        name={name}
        value={name}
        onChange={(e) => {
          filterCategory(category, name, e.target.checked);
        }}
      />
      <label className="capitalize" htmlFor={name}>
        {name}
      </label>
    </dd>
  );
};

interface CategoryItem {
  category: string;
  name: string;
  filterCategory: (category: string, name: string, checked: boolean) => void;
}
