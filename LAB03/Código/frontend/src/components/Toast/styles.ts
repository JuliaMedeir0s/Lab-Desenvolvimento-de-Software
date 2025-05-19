export const toastContainer = (type: "success" | "error" | "info") => {
  const color = {
    success: "green",
    error: "red",
    info: "blue",
  }[type];

  return {
    padding: "10px 16px",
    border: `1px solid ${color}`,
    backgroundColor: `${color}20`,
    color: color,
    borderRadius: "4px",
    marginTop: "10px",
  };
};
