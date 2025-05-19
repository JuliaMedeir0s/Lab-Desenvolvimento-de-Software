import React from "react";
import { tableStyle, thStyle, tdStyle } from "./styles";

interface TableProps {
  headers: string[];
  rows: React.ReactNode[][];
}

function Table({ headers, rows }: TableProps) {
  return (
    <table style={tableStyle}>
      <thead>
        <tr>
          {headers.map((header, index) => (
            <th key={index} style={thStyle}>
              {header}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {rows.map((row, rowIndex) => (
          <tr key={rowIndex}>
            {row.map((cell, cellIndex) => (
              <td key={cellIndex} style={tdStyle}>
                {cell}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default Table;
