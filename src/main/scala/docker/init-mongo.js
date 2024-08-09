// Use or create the database 'testdb'
db = db.getSiblingDB('testdb');

// Sample data for 'employees' collection
db.employees.insertMany([
  { employee_id: 1, name: "Alice", department_id: 101, manager_id: null },
  { employee_id: 2, name: "Bob", department_id: 102, manager_id: 1 },
  { employee_id: 3, name: "Charlie", department_id: 103, manager_id: 1 }
]);

// Sample data for 'departments' collection
db.departments.insertMany([
  { department_id: 101, department_name: "HR" },
  { department_id: 102, department_name: "Engineering" },
  { department_id: 103, department_name: "Sales" }
]);

// Create view for INNER JOIN
db.createView(
  "inner_join_view",
  "employees",
  [
    {
      $lookup: {
        from: "departments",
        localField: "department_id",
        foreignField: "department_id",
        as: "department_info"
      }
    },
    {
      $unwind: "$department_info"
    },
    {
      $project: {
        name: 1,
        department_name: "$department_info.department_name"
      }
    }
  ]
);

// Create view for LEFT JOIN
db.createView(
  "left_join_view",
  "employees",
  [
    {
      $lookup: {
        from: "departments",
        localField: "department_id",
        foreignField: "department_id",
        as: "department_info"
      }
    },
    {
      $project: {
        name: 1,
        department_name: { $arrayElemAt: ["$department_info.department_name", 0] }
      }
    }
  ]
);

// Create view for RIGHT JOIN
db.createView(
  "right_join_view",
  "departments",
  [
    {
      $lookup: {
        from: "employees",
        localField: "department_id",
        foreignField: "department_id",
        as: "employee_info"
      }
    },
    {
      $project: {
        department_name: 1,
        employees: { $arrayElemAt: ["$employee_info.name", 0] }
      }
    }
  ]
);

// Create view for FULL JOIN (simulated with two separate views)
db.createView(
  "full_join_employees_view",
  "employees",
  [
    {
      $lookup: {
        from: "departments",
        localField: "department_id",
        foreignField: "department_id",
        as: "department_info"
      }
    },
    {
      $project: {
        name: 1,
        department_name: { $arrayElemAt: ["$department_info.department_name", 0] }
      }
    }
  ]
);

db.createView(
  "full_join_departments_view",
  "departments",
  [
    {
      $lookup: {
        from: "employees",
        localField: "department_id",
        foreignField: "department_id",
        as: "employee_info"
      }
    },
    {
      $project: {
        department_name: 1,
        employees: { $arrayElemAt: ["$employee_info.name", 0] }
      }
    }
  ]
);

// Create view for CROSS JOIN
db.createView(
  "cross_join_view",
  "employees",
  [
    {
      $lookup: {
        from: "departments",
        pipeline: [
          { $project: { department_name: 1 } }
        ],
        as: "department_info"
      }
    },
    {
      $unwind: "$department_info"
    },
    {
      $project: {
        name: 1,
        department_name: "$department_info.department_name"
      }
    }
  ]
);

// Create view for SELF JOIN
db.createView(
  "self_join_view",
  "employees",
  [
    {
      $lookup: {
        from: "employees",
        localField: "manager_id",
        foreignField: "employee_id",
        as: "manager_info"
      }
    },
    {
      $unwind: "$manager_info"
    },
    {
      $project: {
        employee_name: "$name",
        manager_name: "$manager_info.name"
      }
    }
  ]
);

// Query the views

// Query INNER JOIN view
print("INNER JOIN result:");
printjson(
  db.inner_join_view.find().toArray()
);

// Query LEFT JOIN view
print("LEFT JOIN result:");
printjson(
  db.left_join_view.find().toArray()
);

// Query RIGHT JOIN view
print("RIGHT JOIN result:");
printjson(
  db.right_join_view.find().toArray()
);

// Query FULL JOIN views
print("FULL JOIN Employees result:");
printjson(
  db.full_join_employees_view.find().toArray()
);

print("FULL JOIN Departments result:");
printjson(
  db.full_join_departments_view.find().toArray()
);

// Query CROSS JOIN view
print("CROSS JOIN result:");
printjson(
  db.cross_join_view.find().toArray()
);

// Query SELF JOIN view
print("SELF JOIN result:");
printjson(
  db.self_join_view.find().toArray()
);
