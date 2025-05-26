type ClassValue =
  | string
  | number
  | boolean
  | undefined
  | null
  | { [key: string]: any };

export function cn(...classes: ClassValue[]): string {
  return classes.filter(Boolean).join(" ");
}
