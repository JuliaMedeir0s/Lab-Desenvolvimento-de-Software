import React from "react";
import { cn } from "../../utils/cn";

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: "primary" | "secondary" | "outline";
  fullWidth?: boolean;
  children: React.ReactNode;
}

const Button: React.FC<ButtonProps> = ({
  variant = "primary",
  fullWidth = false,
  className,
  children,
  ...props
}) => {
  const baseStyles =
    "px-4 py-2 rounded-md font-medium transition-all duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2";

  const variantStyles = {
    primary:
      "bg-indigo-600 text-white hover:bg-indigo-700 focus:ring-indigo-500",
    secondary:
      "bg-indigo-100 text-indigo-700 hover:bg-indigo-200 focus:ring-indigo-400",
    outline:
      "border border-indigo-600 text-indigo-600 hover:bg-indigo-50 focus:ring-indigo-400",
  };

  const widthStyles = fullWidth ? "w-full" : "";

  return (
    <button
      className={cn(baseStyles, variantStyles[variant], widthStyles, className)}
      {...props}
    >
      {children}
    </button>
  );
};

export default Button;
