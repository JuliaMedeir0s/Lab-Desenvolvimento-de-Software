export const toastContainer = (
  type: "success" | "error" | "info"
): React.CSSProperties => {
  const colors = {
    success: "#16A34A", 
    error: "#DC2626", 
    info: "#2563EB", 
  };

  const background = {
    success: "#DCFCE7", 
    error: "#FEE2E2", 
    info: "#DBEAFE", 
  };

  return {
    padding: "12px 16px",
    borderRadius: "6px",
    border: `1px solid ${colors[type]}`,
    backgroundColor: background[type],
    color: colors[type],
    marginTop: "12px",
    fontSize: "15px",
    fontWeight: 500,
    boxShadow: "0 2px 6px rgba(0, 0, 0, 0.05)",
  };
};
