export const RightPanel = ({ children }) => {
  return (
    <>
      <ul className="col-span-5 grid grid-cols-3 gap-8 mt-10">{children}</ul>
    </>
  );
};
